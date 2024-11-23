package project.scarfino.ImageDB.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.scarfino.ImageDB.models.Account;
import project.scarfino.ImageDB.models.Image;
import project.scarfino.ImageDB.models.data.AccountRepository;
import project.scarfino.ImageDB.models.data.ImageRepository;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Controller
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository, ImageRepository imageRepository) {
        this.accountRepository = accountRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("overview")
    public String overview(Model model){
        model.addAttribute("title", "Account Overview");
        model.addAttribute("account", accountRepository.findAll());
        return "accounts/overview";
    }

    @GetMapping("add")
    public String displayAddAccountForm(Model model) {
        model.addAttribute(new Account());
        model.addAttribute(new Image());
        model.addAttribute("title","Add Account");
        return "accounts/add";
    }

    @PostMapping(value = "add", consumes = {"application/x-www-form-urlencoded", MULTIPART_FORM_DATA_VALUE })
    public String processAddAccountForm(@ModelAttribute @Valid Account newAccount,
                                        @RequestParam MultipartFile file,
                                        @RequestParam String fileName,
                                        Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Account");
            return "accounts/add";
        }

        Image tempImage = new Image();
        saveImage(tempImage, fileName, file);
        imageRepository.save(tempImage);
        newAccount.setAccountImage(tempImage);
        accountRepository.save(newAccount);

        return "redirect:../";
    }

    @GetMapping("view/{accountId}")
    public String displayViewAccount(Model model, @PathVariable int accountId) {
        Optional optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = (Account) optionalAccount.get();
            model.addAttribute("account", account);
            return "view";
        } else {
            return "redirect:../";
        }
    }

    public void saveImage(Image imageEntity, String name, MultipartFile file) {
        try {
            byte[] imageData = file.getBytes();
            if (imageData.length == 0) {
                throw new IllegalArgumentException("Uploaded file is empty");
            }
            imageEntity.setName(name);
            imageEntity.setImageData(imageData);
            imageRepository.save(imageEntity);

            Logger.getLogger(Image.class.getName()).log(Level.INFO, "Image saved with size: " + imageData.length);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
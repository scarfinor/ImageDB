package project.scarfino.ImageDB.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.scarfino.ImageDB.models.Image;
import project.scarfino.ImageDB.models.User;
import project.scarfino.ImageDB.models.data.ImageRepository;
import project.scarfino.ImageDB.models.data.UserRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private ImageRepository imageRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "All Users");
        model.addAttribute("users", userRepository.findAll());

        return "users/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute(new Image());
        model.addAttribute("title","Add User");
        return "users/add";
    }

    @PostMapping(value = "add", consumes = {"application/x-www-form-urlencoded", MULTIPART_FORM_DATA_VALUE })
    public String processAddUserForm(@ModelAttribute @Valid User newUser, @RequestParam MultipartFile file, @RequestParam String fileName, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add User");
            return "users/add";
        }
        Image tempImage = new Image();
        saveImage(tempImage, fileName, file);
        imageRepository.save(tempImage);
        newUser.setUserImage(tempImage);
        userRepository.save(newUser);

        return "redirect:";
    }

    @GetMapping("view/{userId}")
    public String displayViewUser(Model model, @PathVariable int userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = (User) optionalUser.get();
            model.addAttribute("user", user);
            return "users/view";
        } else {
            return "redirect:../";
        }

    }


    public void saveImage(Image imageEntity, String name, MultipartFile file) {
        try {
            imageEntity.setName(name);
            imageEntity.setImageData(file.getBytes());
            imageRepository.save(imageEntity);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
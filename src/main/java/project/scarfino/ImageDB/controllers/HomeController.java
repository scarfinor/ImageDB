package project.scarfino.ImageDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import project.scarfino.ImageDB.models.data.AccountRepository;
import project.scarfino.ImageDB.models.data.ImageRepository;


@Controller
public class HomeController {


    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    public HomeController(AccountRepository accountRepository, ImageRepository imageRepository) {
        this.accountRepository = accountRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "index";
    }
}

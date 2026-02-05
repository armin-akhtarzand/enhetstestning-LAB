package se.iths.armin.enhetstestninglab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.armin.enhetstestninglab.service.ATMService;

@RequestMapping("/")
@Controller
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }


    @GetMapping("/")
    public String atm(Model model) {
        model.addAttribute("balance", atmService.performCheckAccountBalance());
        return "atm";
    }

}

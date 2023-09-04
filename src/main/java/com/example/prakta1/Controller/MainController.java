package com.example.prakta1.Controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/main")
    String getMain() {
        return "main";
    }

    @GetMapping("/calc")
    String getCalc() {
        return "calc";
    }

    @GetMapping("/converter")
    String getConverter() {
        return "converter";
    }

    @PostMapping("/converter")
    public String postValute(Model model,
                             @RequestParam("Valute") String Valute,
                             @RequestParam("Valute2") String Valute2,
                             @RequestParam("amount") double amount) {
        double RUB = 65.3;
        double DOLLAR = 96.34;
        double EURO = 104.61;
        double YUAN = 13.25;
        double convertetAmount = 0.0;

        if ("RUB".equals(Valute) && "DOLLAR".equals(Valute2)) {
            convertetAmount = amount / DOLLAR;
        } else if ("RUB".equals(Valute) && "EURO".equals(Valute2)) {
            convertetAmount = amount / EURO;
        } else if ("RUB".equals(Valute) && "YUAN".equals(Valute2)) {
            convertetAmount = amount / YUAN;
        } else if ("RUB".equals(Valute) && "RUB".equals(Valute2)) {
            convertetAmount = amount * RUB;
        } else if ("DOLLAR".equals(Valute) && "RUB".equals(Valute2)) {
            convertetAmount = amount * DOLLAR;
        } else if ("DOLLAR".equals(Valute) && "EURO".equals(Valute2)) {
            convertetAmount = amount * (DOLLAR / EURO);
        } else if ("DOLLAR".equals(Valute) && "YUAN".equals(Valute2)) {
            convertetAmount = amount * (DOLLAR / YUAN);
        } else if ("DOLLAR".equals(Valute) && "DOLLAR".equals(Valute2)) {
            convertetAmount = amount;
        } else if ("EURO".equals(Valute) && "YUAN".equals(Valute2)) {
            convertetAmount = amount * (EURO / YUAN);
        } else if ("EURO".equals(Valute) && "RUB".equals(Valute2)) {
            convertetAmount = amount * EURO;
        } else if ("EURO".equals(Valute) && "DOLLAR".equals(Valute2)) {
            convertetAmount = amount * (EURO / DOLLAR);
        } else if ("EURO".equals(Valute) && "EURO".equals(Valute2)) {
            convertetAmount = amount;
        } else if ("YUAN".equals(Valute) && "RUB".equals(Valute2)) {
            convertetAmount = amount * YUAN;
        } else if ("YUAN".equals(Valute) && "DOLLAR".equals(Valute2)) {
            convertetAmount = amount * (YUAN / DOLLAR);
        } else if ("YUAN".equals(Valute) && "EURO".equals(Valute2)) {
            convertetAmount = amount * (YUAN / EURO);
        } else if ("YUAN".equals(Valute) && "YUAN".equals(Valute2)) {
            convertetAmount = amount;
        }


        model.addAttribute("Valute", Valute);
        model.addAttribute("Valute2", Valute2);
        model.addAttribute("amount", amount);
        model.addAttribute("convertetAmount", convertetAmount);

    double roundedAmount = Math.round(convertetAmount * 100.0)/ 1000.0;
    model.addAttribute("convertetAmount", roundedAmount);
        return "converter";
    }

    @PostMapping("/result")
    public String calculateResult(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {
        double result = 0.0;

        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("showErrorBanner", true);
                    model.addAttribute("errorMessage", "Деление на ноль невозможно");
                    return "calc";
                }
                break;
            default:
                model.addAttribute("error", "Неизвестная операция");
                return "calc";
        }

        model.addAttribute("result", result);
        return "result";
    }
}




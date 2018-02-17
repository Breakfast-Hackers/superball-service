package breakfast.hackers.superballservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @Autowired
    private MovementService movementService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("movements", movementService.getMovements());
        return "index";
    }
}

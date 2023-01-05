package ru.fidarov.SpringMVC.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fidarov.SpringMVC.dao.PersonDao;

@Controller
@RequestMapping("/test_batch_update")
public class BatchController {
    private final PersonDao personDao;

    @Autowired
    public BatchController(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping()
    public String index(){
        return "batch/index";
    }

    @GetMapping("/without")
    public String withOutBatch(){
        personDao.testMultipleUpdate();
        return "redirect:/people";

    }
    @GetMapping("/with")
    public String withBatch(){
        personDao.testBatchUpdate();
        return "redirect:/people";

    }


}

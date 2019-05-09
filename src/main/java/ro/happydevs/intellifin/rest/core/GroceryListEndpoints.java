package ro.happydevs.intellifin.rest.core;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/rest/groceries")
public class GroceryListEndpoints {


}

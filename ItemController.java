package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Item;
import com.app.service.IItemService;


@Controller
public class ItemController {
	
	@Autowired
	private IItemService service;
	
	@RequestMapping("/showReg")
	public String  showReg() {
		return "ItemRegister";
	}
	
	@RequestMapping(value="/saveItem",method=RequestMethod.POST)
	public String saveItem(@ModelAttribute("item") Item item,ModelMap map) {
		int itemId = service.save(item);
		String msg = "Item details saved successfully"+itemId;
		map.addAttribute("msg", msg);
		return "ItemRegister";
	}
	
	@RequestMapping("/getAllItems")
	public String getAllitems(ModelMap map) {
		List<Item> itemList=service.getAll();
		map.addAttribute("items", itemList);
		return "ItemData";
	}
   
	@RequestMapping("/deleteItem")
	public String deleteItem(@RequestParam("itemId")int itemId){
		service.delete(itemId);
		return "redirect:getAllItems";
	}
	@RequestMapping("/editItem")
	public String editItem(@RequestParam("itemId")int itemId,ModelMap map) {
		Item item=service.getOne(itemId);
		map.addAttribute("item", item);
		return "ItemDataEdit";
	}
	@RequestMapping("/updateItem")
	public String updateItem(@ModelAttribute("item")Item item) {
		service.update(item);
		return "redirect:getAllItems";
	}
}

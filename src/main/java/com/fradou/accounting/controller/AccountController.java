package com.fradou.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fradou.accounting.model.AccountEntry;
import com.fradou.accounting.model.AccountEntryRepository;

@RestController
public class AccountController {

	@Autowired
	AccountEntryRepository dao;

	@RequestMapping("/entry/{id}")
	public AccountEntry getAccountEntry(@PathVariable("id") int id) {
		return dao.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/entry")
	public int createAccountEntry(@RequestBody AccountEntry entry) {
		return dao.save(entry).getId();
	}
}

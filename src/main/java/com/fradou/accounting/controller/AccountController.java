package com.fradou.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fradou.accounting.model.AccountService;

@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
}

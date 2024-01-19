package com.ccca.cccadriver

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("signup")
class SignupController {

    @PostMapping("/")
    fun signup(@RequestBody account: Account): Any {
        return Signup(account)
    }
}
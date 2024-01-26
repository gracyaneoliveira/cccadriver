package com.ccca.cccadriver

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("signup")
class SignupController(
    private val signup: Signup
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun signup(@RequestBody account: Account): Response {
        val accountSaved = signup.execute(account)
        return Response(accountSaved.accountId!!)
    }
}
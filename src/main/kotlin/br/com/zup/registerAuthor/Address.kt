package br.com.zup.registerAuthor

import javax.persistence.Embeddable

@Embeddable
class Address(
	val street: String,
	val number: String,
	val code: String,
	val city: String
)

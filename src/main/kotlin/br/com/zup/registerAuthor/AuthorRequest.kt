package br.com.zup.registerAuthor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Introspected
data class AuthorRequest(
	@field:NotBlank
	val name: String,
	@field:NotBlank
	@field:Email
	val email: String,
	val cpf: String
) {
	fun toAuthor(): Author = Author(name, email, cpf)
}

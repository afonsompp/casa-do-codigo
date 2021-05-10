package br.com.zup.registerAuthor

class AuthorResponse(author: Author) {
	val id = author.id
	val name = author.name
	val email = author.email
	val cpf = author.cpf
}

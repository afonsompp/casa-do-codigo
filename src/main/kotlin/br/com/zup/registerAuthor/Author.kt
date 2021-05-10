package br.com.zup.registerAuthor

import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Author(
	val name: String,
	var email: String,
	val cpf: String,
	@Embedded
	val address: Address,
	@Id
	@GeneratedValue
	val id: Long? = null

)

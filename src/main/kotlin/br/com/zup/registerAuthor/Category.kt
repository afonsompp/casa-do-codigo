package br.com.zup.registerAuthor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category(
	@Column(nullable = false)
	val name: String,
	@Id
	@GeneratedValue
	val id: Long? = null
)

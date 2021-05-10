package br.com.zup.registerAuthor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class CategoryRequest(
	@field:NotBlank
	@field:Size(min = 5, max = 60)
	val name: String
) {
	fun toCategory(): Category {
		return Category(name)
	}
}

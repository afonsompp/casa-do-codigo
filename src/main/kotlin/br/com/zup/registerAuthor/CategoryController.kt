package br.com.zup.registerAuthor

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/category")
@Validated
class CategoryController(val repository: CategoryRepository) {

	@Post
	fun save(@Body @Valid request: CategoryRequest): HttpResponse<CategoryResponse> {
		val saved = repository.save(request.toCategory())

		return HttpResponse.ok(CategoryResponse(saved))
	}


}

package br.com.zup.registerAuthor

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.http.server.util.locale.HttpLocaleResolver
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

typealias ListAuthorResponse = List<AuthorResponse>

@Controller("/author")
@Validated
class AuthorController(val repository: AuthorRepository, val addressClient: AddressHttpClient) {

	@Post
	fun register(@Body @Valid request: AuthorRequest): HttpResponse<AuthorResponse> {
		val address = addressClient.getAddress("00000-000")
		val saved = repository.save(request.toAuthor(address.body()!!))

		val uri = UriBuilder.of("/author/{id}")
			.expand(mutableMapOf(Pair("id", saved.id)))
		return HttpResponse.created<AuthorResponse>(uri).body(AuthorResponse(saved))
	}

	@Get
	fun getByEmail(@QueryValue(defaultValue = "") email: String): HttpResponse<ListAuthorResponse> {
		val authors =
			if (email.isNotBlank()) repository.findByEmail(email)
			else repository.findAll()
		return HttpResponse.ok(authors.map(::AuthorResponse))
	}

	@Get("/{cpf}")
	fun getByCpf(@PathVariable cpf: String): HttpResponse<AuthorResponse> {

		val author = repository.findCpf(cpf).orElseThrow {
			HttpStatusException(HttpStatus.NOT_FOUND, "not found")
		}
		return HttpResponse.ok(AuthorResponse(author))
	}

	@Put("/{id}")
	@Transactional
	fun update(id: Long, @NotBlank @Email email: String): HttpResponse<AuthorResponse> {
		val author = repository.findById(id).orElseThrow {
			HttpStatusException(HttpStatus.NOT_FOUND, "not found")
		}
		author.email = email

		return HttpResponse.ok(AuthorResponse(author))
	}

	@Delete("/{id}")
	fun delete(id: Long): HttpResponse<Any> {
		repository.findById(id).orElseThrow {
			HttpStatusException(HttpStatus.NOT_FOUND, "not found")
		}

		repository.deleteById(id)

		return HttpResponse.noContent()
	}
}

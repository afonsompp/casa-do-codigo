package br.com.zup.registerAuthor

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8080/address")
interface AddressHttpClient {

	@Get(consumes = [MediaType.APPLICATION_XML])
	fun getAddress(code: String): HttpResponse<AddressResponse>
}

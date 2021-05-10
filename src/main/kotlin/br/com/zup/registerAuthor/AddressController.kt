package br.com.zup.registerAuthor

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/address")
class AddressController {

	@Get(produces = [MediaType.APPLICATION_XML])
	fun getAddress(): HttpResponse<AddressResponse> {

		val address = AddressResponse(
			"Rua dos bobos",
			"0000",
			"00000-000",
			"de ninguém"
		)
		return HttpResponse.ok(address).contentType(MediaType.APPLICATION_XML)
	}
}

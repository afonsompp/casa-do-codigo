package br.com.zup.registerAuthor

data class AddressResponse(
	val street: String,
	val number: String,
	val code: String,
	val city: String
) {
	fun toAddress() = Address(street, number, code, city)
}

package br.com.zup.registerAuthor

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {

	fun findByEmail(email: String): List<Author>

	@Query("SELECT a FROM Author a WHERE a.cpf = :cpf")
	fun findCpf(cpf: String): Optional<Author>
}

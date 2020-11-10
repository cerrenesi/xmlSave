package xmlsave.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import xmlsave.entity.SaveBuffer

@Repository
interface BufferRepository: JpaRepository<SaveBuffer, Long> {

    @Modifying
    fun deleteAllByUuid(uuid: String)

}
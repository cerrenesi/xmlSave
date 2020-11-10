package xmlsave

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import xmlsave.controller.SaveDataController

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
class XmlsaveApplication

fun main(args: Array<String>) {
	val context = runApplication<XmlsaveApplication>(*args)
}

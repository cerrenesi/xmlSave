package xmlsave.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xmlsave.entity.GoodsPrice
import xmlsave.service.GoodsPriceService
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/api")
class SaveDataController(private val goodsPriceService: GoodsPriceService) {

    @PostMapping("/saveViaJPA")
    fun saveDataViaJPA(@RequestParam count: Int) {
        val timeStart = System.currentTimeMillis()
        goodsPriceService.saveAll(prepareData(count))
        val secSpent = (System.currentTimeMillis() - timeStart) / 60
        logger.info("Seconds spent : $secSpent")
    }

    @PostMapping("/saveViaBuffer")
    fun saveViaBuffer(@RequestParam count: Int) {
        val timeStart = System.currentTimeMillis()
        goodsPriceService.saveViaBuffer(prepareData(count))
        val secSpent = (System.currentTimeMillis() - timeStart) / 60
        logger.info("Seconds spent : $secSpent")
    }


    private fun prepareData(count: Int) : List<GoodsPrice> {
        val prices = mutableListOf<GoodsPrice>()

        for (i in 1..count) {
            prices.add(GoodsPrice(
                    id = 0L,
                    priceDate = LocalDate.now().minusDays(i.toLong()),
                    goodsId = 1L,
                    price = BigDecimal.TEN
            ))
        }
        return prices

    }

    companion object {
        private val logger = LoggerFactory.getLogger(SaveDataController::class.java)
    }

}
package social.alone.server.link


import org.modelmapper.ModelMapper
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import social.alone.server.config.AppProperties
import social.alone.server.event.domain.Event

@Controller
@RequestMapping(value = ["/api/events/{eventId}/links"])
class LinkController(private val appProperties: AppProperties, private val modelMapper: ModelMapper, private val linkService: LinkService) {

    @PostMapping
    fun createLink(
            @PathVariable("eventId") event: Event?
    ): ResponseEntity<*> {
        if (event == null) {
            return ResponseEntity.notFound().build<Any>()
        }

        val link = linkService!!.createLink(event)

        // TODO view logic
        val response = modelMapper!!.map(link, LinkResponseDTO::class.java)
        response.url = appProperties!!.link.host + "/api/events/" + event.id + "/links"

        return ResponseEntity.ok(response)
    }

    @GetMapping(produces = [MediaType.TEXT_HTML_VALUE])
    fun getLink(
            @PathVariable("eventId") event: Event?
    ): ResponseEntity<*> {

        return if (event == null) {
            ResponseEntity.notFound().build<Any>()
        } else ResponseEntity.ok(event.linkHtml)

    }

}
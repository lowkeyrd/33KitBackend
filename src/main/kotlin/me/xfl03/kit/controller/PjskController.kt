package me.xfl03.kit.controller

import me.xfl03.kit.exception.ForbiddenException
import me.xfl03.kit.request.DownloadRequest
import me.xfl03.kit.response.DownloadResponse
import me.xfl03.kit.service.CaptchaService
import me.xfl03.kit.service.CdnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class PjskController {
    @Autowired
    lateinit var captchaService: CaptchaService

    @Autowired
    lateinit var cdnService: CdnService

    @PostMapping("/pd")
    fun download(response: HttpServletResponse, @RequestBody downloadRequest: DownloadRequest): DownloadResponse {
        val name = downloadRequest.filename
        if (name.contains("/") || !(name.endsWith(".apk") || name.endsWith(".ipa"))) {
            throw ForbiddenException("Filename not allowed")
        }
        if (downloadRequest.token == null && downloadRequest.recaptchaToken == null) {
            throw ForbiddenException("Captcha needed")
        }
        if (downloadRequest.recaptchaToken != null) {
            val recaptchaResult = captchaService.checkRecaptchaV3(downloadRequest.recaptchaToken)
            if (!recaptchaResult.first) {
                throw ForbiddenException("Recaptcha not success with score ${recaptchaResult.second}")
            }
        }
        if (downloadRequest.token != null && !captchaService.checkHcaptcha(downloadRequest.token)) {
            throw ForbiddenException("Hcaptcha not success")
        }

        return DownloadResponse(cdnService.getAliyunCdnUrl(name))
    }

//    @RequestMapping("/pd", method = [RequestMethod.OPTIONS])
//    fun downloadOption():String {
//        return "Oh my dear CORS preflight"
//    }
}
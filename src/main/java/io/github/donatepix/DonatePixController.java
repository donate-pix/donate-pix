package io.github.donatepix;

import io.micronaut.http.annotation.*;

@Controller("/donate-pix")
public class DonatePixController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}
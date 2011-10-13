package org.jbehave.web.selenium

import com.github.tanob.groobe.GrooBe
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit
import org.openqa.selenium.WebDriver
import com.google.common.base.Predicate
import org.openqa.selenium.NoSuchElementException

public class GroovyFluentWebDriverPage extends FluentWebDriverPage {

    public GroovyFluentWebDriverPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        GrooBe.activate()
    }

    def waitFor(int timeout, int retry, Class<? extends RuntimeException>[] ignoreThese = new Class<? extends RuntimeException>[0], Closure block) {
        def wdw = new WebDriverWait(webDriver(), timeout)
                        .pollingEvery(retry, TimeUnit.MILLISECONDS)
                        .ignoring(ignoreThese)
                        .ignoring(NoSuchElementException.class) // standard
        return wdw.until(new Predicate<WebDriver>() {
            boolean apply(WebDriver wd) {
                block()
            }
        })
    }

}
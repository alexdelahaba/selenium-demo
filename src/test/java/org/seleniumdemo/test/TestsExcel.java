package org.seleniumdemo.test;

import org.junit.Test;

import static org.seleniumdemo.utils.GuardarDatos.hacerCopiaDeSeguridad;

public class TestsExcel {

    @Test
    public void hacerCopiaSeguridad() {
        hacerCopiaDeSeguridad();
    }
}

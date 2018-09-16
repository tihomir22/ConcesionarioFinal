/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author sportak
 */
public class AppendTheObjectNow extends ObjectOutputStream {

    public AppendTheObjectNow(OutputStream out) throws IOException {
        super(out);
    }

    public AppendTheObjectNow() throws IOException, SecurityException {
        super();
    }

    @Override
    protected void writeStreamHeader() {

    }

}

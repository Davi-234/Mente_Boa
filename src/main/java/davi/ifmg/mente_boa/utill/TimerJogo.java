package davi.ifmg.mente_boa.utill;

import javax.swing.Timer;
import java.util.function.IntConsumer;

public class TimerJogo {

    private Timer timer;
    private int segundos;

    private IntConsumer aoAtualizar;
    private Runnable aoTerminar;

    public TimerJogo(int segundos) {
        this.segundos = segundos;
    }

    public void iniciar() {

        timer = new Timer(1000, e -> {

            segundos--;

            if (aoAtualizar != null) {
                aoAtualizar.accept(segundos);
            }

            if (segundos <= 0) {
                parar();

                if (aoTerminar != null) {
                    aoTerminar.run();
                }
            }
        });

        timer.start();
    }

    public void parar() {

        if (timer != null) {
            timer.stop();
        }
    }

    public void reiniciar(int segundos) {

        parar();

        this.segundos = segundos;

        iniciar();
    }

    public int getSegundos() {
        return segundos;
    }
    
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public boolean estaRodando() {
        return timer != null && timer.isRunning();
    }

    public void setAoAtualizar(IntConsumer aoAtualizar) {
        this.aoAtualizar = aoAtualizar;
    }

    public void setAoTerminar(Runnable aoTerminar) {
        this.aoTerminar = aoTerminar;
    }
}
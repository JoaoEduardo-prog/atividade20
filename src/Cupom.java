import java.time.LocalDate;

public class Cupom {
    private long codigo;
    private double desconto;
    private LocalDate dataValidade;

    public Cupom(long codigo, double desconto, LocalDate dataValidade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.dataValidade = dataValidade;
    }

    public long getCodigo() {
        return codigo;
    }

    public double getDesconto() {
        return desconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    @Override
    public String toString() {
        return "Cupom{codigo=" + codigo + ", desconto=" + desconto + "%, validade=" + dataValidade + "}";
    }
}
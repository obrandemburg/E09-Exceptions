import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas {

    private int numero;

    private Cliente dono;

    private double saldo;

    protected double limite;

    private List<Operacao> operacoes;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        setLimite(limite);

        this.operacoes = new ArrayList<>();

        Conta.totalContas++;
    }

    public void sacar(double valor) throws ValorNegativoException, SemLimiteException, ValorInvalidoException{
        if (valor < 0) {

            throw new ValorNegativoException();
        } else if (valor > this.limite) {

            throw new SemLimiteException();
        } else if (valor > this.saldo) {

            throw new ValorInvalidoException();
        } else{

            this.saldo -= valor;
            this.operacoes.add(new OperacaoSaque(valor));
        }
    }

    public void depositar(double valor) throws ValorNegativoException {
        if (valor < 0)
            throw new ValorNegativoException();

        this.saldo += valor;
        this.operacoes.add(new OperacaoDeposito(valor));
    }

    public void transferir(Conta destino, double valor) {

        try{
            this.sacar(valor);
            destino.saldo += valor;

        }catch(ValorNegativoException e){
            System.out.println("Erro no saque\n" + e.getMessage());
        }catch(SemLimiteException e){
            System.out.println("Erro no saque\n" + e.getMessage());
        }catch(ValorInvalidoException e){
            System.out.println("Erro no saque\n" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return dono.toString() + '\n' +
                "---\n" +
                "numero=" + numero + '\n' +
                "saldo=" + saldo + '\n' +
                "limite=" + limite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Conta) {
            Conta conta = (Conta) o;
            return numero == conta.numero;
        }
        return false;
    }

    public void imprimirExtrato(int modo) {
        // realiza a cópia para não perder a ordem original da lista
        List<Operacao> operacoesParaExtrato = new ArrayList<>(this.operacoes);

        // Ordena de modo == 1, caso contrário mantém ordem original
        if (modo == 1) {
            Collections.sort(operacoesParaExtrato);
        }

        System.out.println("======= Extrato Conta " + this.numero + "======");
        for(Operacao atual : operacoesParaExtrato) {
            System.out.println(atual);
        }
        System.out.println("====================");
    }

    public void imprimirExtratoTaxas() {
        System.out.println("=== Extrato de Taxas ===");
        System.out.printf("Manutenção:\t%.2f\n", this.calcularTaxas());

        double totalTaxas = this.calcularTaxas();
        for (Operacao atual : this.operacoes) {
            totalTaxas += atual.calcularTaxas();
            System.out.printf("%c:\t%.2f\n", atual.getTipo(), atual.calcularTaxas());
        }

        System.out.printf("Total:\t%.2f\n", totalTaxas);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract void setLimite(double limite) throws IllegalArgumentException;
}

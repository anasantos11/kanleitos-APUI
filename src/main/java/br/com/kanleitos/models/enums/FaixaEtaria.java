package br.com.kanleitos.models.enums;

public enum FaixaEtaria {
	De0A4(0), De5A9(5), De10A14(10), De15A20(15), De20A24(20), De25A29(25), De30A34(30), De35A39(35), De40A44(
			40), De45A49(
					45), De50A54(50), De55A59(55), De60A64(60), De65A69(65), De70A74(70), De75A79(75), ApartirDe80(80);

	public int idade;

	private FaixaEtaria(int idade) {
		this.idade = idade;
	}
}

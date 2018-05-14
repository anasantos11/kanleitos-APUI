package br.com.kanleitos.models.enums;

public enum FaixaEtaria {
	De0A4(0, "0 a 4"), De5A9(5, "5 a 9"), De10A14(10, "10 a 14"), De15A20(15, "15 a 20"), De20A24(20, "20 a 24"), De25A29(25,
			"25 a 29"), De30A34(30, "30 a 34"), De35A39(35, "35 a 39"), De40A44(40, "40 a 44"), De45A49(45,
					"45 a 49"), De50A54(50, "50 a 54"), De55A59(55, "55 a 59"), De60A64(60, "60 a 64"), De65A69(65,
							"65 a 69"), De70A74(70, "70 a 74"), De75A79(75, "75 a 79"), ApartirDe80(80, "A partir de 80");

	public int idade;
	public String nome;

	private FaixaEtaria(int idade, String nome) {
		this.idade = idade;
		this.nome = nome;
	}
	
}

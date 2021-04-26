/**
 * 
 */
package com.algaworks.cadastrocliente.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.cadastrocliente.model.Cliente;

/**
 * 
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Faz a interação c/ BD com base nas configurações do persistence.xml
		// Vc pode criar outras factories para outras unidades de persistência.
		// Essa instância pode ficar acessível durante todo o ciclo de vida da app.
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("Clientes-PU");
		
		// Gerente de entidades
		// Embora não seja uma regra, em aplicaçoes web, entityManagers nascem e se encerram
		// junto com o início e o fim da  requisição, respectivamente.
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		System.out.println(cliente.getNome());
		
		// Fechando entityManager e entityManagerFactory.
		entityManager.close();
		entityManagerFactory.close();

	}

}
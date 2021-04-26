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
		
		// Buscando um cliente no DB.
		Cliente cliente = entityManager.find(Cliente.class, 1);
		System.out.println(cliente.getNome());
		
		// Inserindo registro
		cliente = new Cliente();
		cliente.setNome("Eletro Silva");
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(cliente);
		
		entityManager.getTransaction().commit();
		
		
		// Removendo um registro
		// A gente só consegue remover objetos que o entityManager sabe que existem(já está na mem[oria dele, sob seu gerenciamento)
		cliente = entityManager.find(Cliente.class, 6); // automaticamente o entityManager coloca o objeto buscado como gerenciado
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		// Atualizando um registro de forma gerenciada
		cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		cliente.setNome(cliente.getNome() + " Alterado");
		entityManager.getTransaction().commit();
		
		// Atualizando um registro de forma não gerenciada
		// Suponha que vc montou o seu novo objeto com base nas informações vindas do front-end:
		cliente = new Cliente(); // note que este objeto não está gerenciado pelo entityManager(ou seja, não está em sua memória)
		cliente.setId(1);
		cliente.setNome("Armazem Estrela");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente); // Não é obrigatório estar entre begin() commit(), mas é recomendado.
		entityManager.getTransaction().commit();
		
		
		// O merge() pode ser usado para fazer inserção, caso vc não passe o id. Vide;
		cliente = new Cliente();
		cliente.setNome("Construtora Ferreira");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
			
		
		// Fechando entityManager e entityManagerFactory.
		entityManager.close();
		entityManagerFactory.close();

	}

}

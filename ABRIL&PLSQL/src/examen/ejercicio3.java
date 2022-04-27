package examen;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Date;

public class ejercicio3 {
	/**
	 3.
CREATE TYPE PEDI2 AS TABLE OF PEDIDOS;
//
CREATE TABLE COMPRAS2 (
clien CLIENTE,
pedid PEDI2
)
NESTED TABLE pedi2 STORE AS PedidosT;
	  
	 */
	
	public static void insertar() {
		//A)
		try {
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system","1234");
			Statement sentencia=conexion.createStatement();
			String insert1 = "INSERT INTO COMPRAS2 VALUES(clien"
			          + "(1, 'Aarón Rivero Gómez', 'Almería', 100)"
			          + ",ped("
			          + "pedid(2, 270.65, '2016-09-10),"
			          + "pedid(15, 370.85, '2019-03-11'),"
			          + "pedid(16, 2389.23, '2019-03-11')))";
			String insert2 = "INSERT INTO COMPRAS2 VALUES(clien"
			          + "(2, 'Adela Salas Díaz', 'Granada', 200)"
			          + ",ped("
			          + "pedid(3, 65.26, '2017-10-05'),"
			          + "pedid(7, 5760, '2015-09-10'),"
			          + "pedid(12, 3045.6, '2017-04-25'),"
			          + "pedid(6, 2400.6, '2016-07-27')))";
			String insert3 = "INSERT INTO COMPRAS2 VALUES(clien"
			          + "(3, 'Adolfo Rubio Flores', 'Sevilla', 150)"
			          + ",ped("
			          + "pedid(11, 75.29, '2016-08-17'),"
			          + "pedid(13, 545.75, '2019-01-25'),"
			          + "pedid(14, 145.82, '2017-02-02')))";
			String insert4 = "INSERT INTO COMPRAS2 VALUES(clien"
			          + "(4, 'Adrián Suárez Gómez', 'Jaén', 300)"
			          + ",ped("
			          + "pedid(8, 1983.43, '2017-10-10'),"
			          + "pedid(4, 110.5, '2016-08-17'),"
			          + "pedid(9, 2480.4, '2016-10-10'),"
			          + "pedid(10, 250.45, '2015-06-27')))";
			String insert5 = "INSERT INTO COMPRAS2 VALUES(clien"
			          + "(5, 'Marcos Loyola Méndez', 'Almería', 200)"
			          + ",ped("
			          + "pedid(1, 150.5, '2017-10-05'),"
			          + "pedid(5, 948.5, '2017-09-10')))";
			sentencia.execute(insert1);
			sentencia.execute(insert2);
			sentencia.execute(insert3);
			sentencia.execute(insert4);
			sentencia.execute(insert5);
			sentencia.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void consulta() {
		//B)
		try {
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system","1234");
			Statement sentencia=conexion.createStatement();
			//i
			String consulta1="SELECT * FROM COMPRAS2 C";
			ResultSet rs1 = sentencia.executeQuery(consulta1);

			while(rs1.next()) {
				Struct clientes = (Struct) rs1.getObject(1);

                Object[] atrCLien= clientes.getAttributes();
                
				for(int i=0;i<atrCLien.length;i++) {
					Struct cliente= (Struct) atrCLien[i];
					Object [] atributosCliente = cliente.getAttributes();
					
					BigDecimal id = (BigDecimal) atributosCliente[0];
	                String nombre = (String) atributosCliente[1];
	                String ciudad = (String) atributosCliente[2];
	                BigDecimal comision = (BigDecimal) atributosCliente[3];
	                //Date fecha = (Date) atributosCliente[3];
	                System.out.println(id);
					System.out.println(nombre);
					System.out.println(comision);
					
				}
				
				Array array= (Array) rs1.getObject(2);
				Object[] pedidos= (Object[]) array.getArray();
				for(int i=0;i<pedidos.length;i++) {
					Struct pedido= (Struct) pedidos[i];
					Object [] atributosPedidos = pedido.getAttributes();
					BigDecimal id = (BigDecimal) atributosPedidos[0];
					BigDecimal total = (BigDecimal) atributosPedidos[1];
					Date fecha = (Date) atributosPedidos[2];
					System.out.println(id);
					System.out.println(total);
					System.out.println(fecha);
				}
			}
			
			rs1.close();
			
			// ii
			
			String consulta2="SELECT * FROM COMPRAS2 C where c.pedidos.total >1000";
			ResultSet rs2 = sentencia.executeQuery(consulta2);
			while(rs2.next()) {
				Struct clientes = (Struct) rs1.getObject(1);

                Object[] atrCLien= clientes.getAttributes();
                
                for(int i=0;i<atrCLien.length;i++) {
					Struct cliente= (Struct) atrCLien[i];
					Object [] atributosCliente = cliente.getAttributes();
					
					BigDecimal id = (BigDecimal) atributosCliente[0];
	                String nombre = (String) atributosCliente[1];
	                String ciudad = (String) atributosCliente[2];
	                BigDecimal comision = (BigDecimal) atributosCliente[3];
	                //Date fecha = (Date) atributosCliente[3];
	                //System.out.println(id);
	                //Como solo pide el id puedo reutilizar el codigo de antes y comentar alguna cosa.
	                
					System.out.println(nombre);
					System.out.println(comision);
					
				}
				
				Array array= (Array) rs1.getObject(2);
				Object[] pedidos= (Object[]) array.getArray();
				for(int i=0;i<pedidos.length;i++) {
					Struct pedido= (Struct) pedidos[i];
					Object [] atributosPedidos = pedido.getAttributes();
					BigDecimal id = (BigDecimal) atributosPedidos[0];
					BigDecimal total = (BigDecimal) atributosPedidos[1];
					Date fecha = (Date) atributosPedidos[2];
					//System.out.println(id);
					//System.out.println(total);
					//System.out.println(fecha);
				}
			}
			rs2.close();
			
			
			String consulta3="SELECT * FROM COMPRAS2 C where c.pedidos.fecha >'01/01/2019' and c.pedidos.fecha < '01/01/2020'";
			ResultSet rs3 = sentencia.executeQuery(consulta3);
			while(rs3.next()) {
				Struct clientes = (Struct) rs1.getObject(1);

                Object[] atrCLien= clientes.getAttributes();
                
                for(int i=0;i<atrCLien.length;i++) {
					Struct cliente= (Struct) atrCLien[i];
					Object [] atributosCliente = cliente.getAttributes();
					
					BigDecimal id = (BigDecimal) atributosCliente[0];
	                String nombre = (String) atributosCliente[1];
	                String ciudad = (String) atributosCliente[2];
	                BigDecimal comision = (BigDecimal) atributosCliente[3];
	                //Date fecha = (Date) atributosCliente[3];
	                //System.out.println(id);
	                //Como solo pide el id puedo reutilizar el codigo de antes y comentar alguna cosa.
	                
					System.out.println(nombre);
					//System.out.println(comision);
					
				}
				
				Array array= (Array) rs1.getObject(2);
				Object[] pedidos= (Object[]) array.getArray();
				for(int i=0;i<pedidos.length;i++) {
					Struct pedido= (Struct) pedidos[i];
					Object [] atributosPedidos = pedido.getAttributes();
					BigDecimal id = (BigDecimal) atributosPedidos[0];
					BigDecimal total = (BigDecimal) atributosPedidos[1];
					Date fecha = (Date) atributosPedidos[2];
					System.out.println(id);
					//System.out.println(total);
					//System.out.println(fecha);
				}
			}
			rs3.close();
			
			
			String consulta4="SELECT * FROM COMPRAS2";
			//SELECT MAX('total') FROM COMPRAS2.PEDI2
			ResultSet rs4 = sentencia.executeQuery(consulta4);
			while(rs4.next()) {
				Struct clientes = (Struct) rs1.getObject(1);

                Object[] atrCLien= clientes.getAttributes();
                
                for(int i=0;i<atrCLien.length;i++) {
					Struct cliente= (Struct) atrCLien[i];
					Object [] atributosCliente = cliente.getAttributes();
					
					BigDecimal id = (BigDecimal) atributosCliente[0];
	                String nombre = (String) atributosCliente[1];
	                //String ciudad = (String) atributosCliente[2];
	                //BigDecimal comision = (BigDecimal) atributosCliente[3];
	                //Date fecha = (Date) atributosCliente[3];
	                //System.out.println(id);
	                //Como solo pide el id puedo reutilizar el codigo de antes y comentar alguna cosa.
	                
					System.out.println(nombre);
					//System.out.println(comision);
					
				}
				
				Array array= (Array) rs1.getObject(2);
				Object[] pedidos= (Object[]) array.getArray();
				for(int i=0;i<pedidos.length;i++) {
					Struct pedido= (Struct) pedidos[i];
					Object [] atributosPedidos = pedido.getAttributes();
					BigDecimal id = (BigDecimal) atributosPedidos[0];
					BigDecimal total = (BigDecimal) atributosPedidos[1];
					Date fecha = (Date) atributosPedidos[2];
					System.out.println(id);
					System.out.println(total);
					System.out.println(fecha);
				}
			}
			rs4.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void eliminar() {
		//D)
		try {
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system","1234");
			// 2. Crear un Statement
			Statement sentencia=conexion.createStatement();
			// 3. Ejecutarlo
			String sql= "DELETE FROM TABLE "
					+ "(SELECT clien FROM PedidosT WHERE ID = 2) P "
					+ "WHERE "
					+ "VALUE(P)=pedid(3, 65.26, '2017-10-05')";
			sentencia.execute(sql);
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void transaccion() {
		//E)
		Connection conexion=null;
		try {
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system","1234");
			// 2. Crear un Statement
			Statement sentencia=conexion.createStatement();
			// 3. Ejecutarlo
			String sql= "UPDATE TABLE "
					+ "(SELECT CLIEN FROM COMPRAS2 WHERE ID = 5) C "
					+ "SET VALUE(C) = pedido(16, 2389.23, '2019-03-11'))";
			String sql2= "DELETE FROM TABLE "
					+ "(SELECT CLIEN FROM COMPRAS2 WHERE ID = 1) C "
					+ "WHERE "
					+ "VALUE(C)=PEDIDO(16, 2389.23, '2019-03-11')";
			conexion.setAutoCommit(false);
			sentencia.execute(sql);
			sentencia.execute(sql2);
			conexion.commit();
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {

		insertar();
		consulta();
		eliminar();
		transaccion();
	}
}

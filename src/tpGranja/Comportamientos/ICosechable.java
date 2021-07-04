package tpGranja.Comportamientos;


import tpGranja.Productos.AbstractProducto;

public interface ICosechable {

	// Hace que una planta sea cosechable, similar al IRecolectable de los animales
	
	AbstractProducto Cosechar();
}

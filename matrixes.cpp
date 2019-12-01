#include <iostream>
#include <math.h>

using namespace std;


int matrix_input(int row, int column){

	if(row<= 0 || column<=0){

		cout << "Wrong matrix dimension" << endl;
		return 0;
	}
	
	else{

	int matrix[row][column];		

	for(int i = 0; i<row; i++){
		
		for(int j = 0; j<column; j++){

			cout << i+1 << " row, " << j+1 << " column: ";
			cin >> matrix[i][j];
		}
		
		cout << endl;
	}

	return **matrix;
	}
}

void matrix_output(int **matrix, int rows, int columns){

	for(int i = 0; i<rows; i++){

		for(int j = 0; j<columns; j++){

			cout << matrix[i][j] << '\t';

		}
		cout << endl;
	}
}

int main(){

	int row_1, column_1;
	
	cout << "Enter the matrix dimensions" << endl;
	cout << "Rows: ";
	cin >> row_1;
	cout << "Columns: ";
	cin >> column_1;

	int matrix_1[row_1][column_1];


	**matrix_1 = matrix_input(row_1, column_1);
	
	matrix_output((int **)matrix_1, row_1, column_1);



	return 0;

}




























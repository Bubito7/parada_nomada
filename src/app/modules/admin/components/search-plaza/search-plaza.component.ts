import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-search-plaza',
  templateUrl: './search-plaza.component.html',
  styleUrl: './search-plaza.component.scss'
})
export class SearchPlazaComponent {

  searchPlazaForm!: FormGroup;
  isSpinning = false;
  plazas: any[] = [];
  listOfPlazas = [
    "PLAZA 01", "PLAZA 02", "PLAZA 03", "PLAZA 04", "PLAZA 05",
    "PLAZA 06", "PLAZA 07", "PLAZA 08", "PLAZA 09", "PLAZA 10",
    "PLAZA 11", "PLAZA 12", "PLAZA 13", "PLAZA 14", "PLAZA 15"
  ];

  listOfType = ["Autocaravana", "Caravana", "Camper"];

  constructor(private fb: FormBuilder, private service:AdminService) {
    this.searchPlazaForm = this.fb.group({
      nombre: [""],
      tipo: [""],
      electricidad: [""],
      agua: [""],

    })
  }

  searchPlaza() {
    this.isSpinning = true;
    let formData = this.searchPlazaForm.value;

    // Crear un objeto solo con los filtros seleccionados
    let filtros: any = {};
    if (formData.nombre?.trim()) {
      filtros.nombre = formData.nombre.trim();
    }
    if (formData.tipo) {
      filtros.tipo = formData.tipo;
    }
    if(formData.electricidad){
      filtros.electricidad = formData.electricidad;
    }
    if(formData.agua){
      filtros.agua = formData.agua;
    }

    console.log("Filtros enviados al backend:", filtros);

    this.service.searchPlaza(filtros).subscribe(
      (res) => {
        console.log("Respuesta del backend:", res);
        this.plazas = res.plazaDtoList; // Almacenar las plazas recibidas
        this.isSpinning = false;
      },
      (error) => {
        console.error("Error al buscar plazas:", error);
        this.isSpinning = false;
      }
    );
  }



}



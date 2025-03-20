import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.scss'
})
export class CustomerDashboardComponent {

  plazas: any = [];

  constructor(private service: CustomerService) {}

  ngOnInit () {
    this.getAllPlazas();
  }

  getAllPlazas() {
    this.service.getAllPlazas().subscribe(
      (res: any[]) => {
        console.log(res);  // Verifica la respuesta para asegurarte de que contiene returnedImg

        res.forEach(plaza => {
          if (plaza.returnedImage) {
            plaza.processedImg = 'data:image/png;base64,' + plaza.returnedImage;
          }


          plaza.descripcion = plaza.descripcion || 'No disponible';
          plaza.tipo = plaza.tipo || 'No especificado';

          this.plazas.push(plaza);
        });
      },
      (err) => {
        console.error('Error al obtener las plazas', err);
      }
    );
  }

}

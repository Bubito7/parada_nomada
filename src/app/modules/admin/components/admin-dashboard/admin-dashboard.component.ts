import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {

  plazas: any = [];

  constructor (private adminService: AdminService, private message: NzMessageService) {}



  ngOnInit () {
    this.getAllPlazas();
  }

  getAllPlazas() {
    this.adminService.getAllPlazas().subscribe(
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

  deletePlaza(id: number) {
    console.log(id);
    this.adminService.deletePlaza(id).subscribe(() => {
      this.message.success("Plaza eliminada con éxito", { nzDuration: 5000 });
      this.plazas = []; // Vacía la lista antes de volver a cargar
      this.getAllPlazas();
    }, err => {
      this.message.error("Error al eliminar la plaza", { nzDuration: 5000 });
    });
  }




}

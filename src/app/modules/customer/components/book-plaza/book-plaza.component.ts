import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../../auth/services/storage/storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-book-plaza',
  templateUrl: './book-plaza.component.html',
  styleUrl: './book-plaza.component.scss'
})
export class BookPlazaComponent {

  plazaId:number = this.activatedRoute.snapshot.params["id"];
  plaza: any;
  isDetailPage: boolean = false;
  validateForm!: FormGroup;
  isSpinning = false;
  dateFormat: "DD-MM-YYYY" | undefined;


  constructor(private service: CustomerService, private activatedRoute: ActivatedRoute, private fb: FormBuilder,
    private router: Router, private message: NzMessageService
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      toDate: [null, Validators.required],
      fromDate: [null, Validators.required],
    })
    this.getPlazaById();
    this.checkIfDetailPage();
  }

  getPlazaById() {
    this.service.getPlazaById(this.plazaId).subscribe((res) => {
      console.log(res);
      this.plaza = res;

      // Si la plaza tiene una imagen en base64, la procesamos correctamente
      if (this.plaza?.returnedImage) {
        this.plaza.processedImg = 'data:image/png;base64,' + this.plaza.returnedImage;
      }
    });
  }

  bookAPlaza(data: any) {
    console.log(data);
    this.isSpinning = true;
    let bookAPlazaDto = {
      toDate: data.toDate,
      fromDate: data.fromDate,
      userId: StorageService.getUserId(),
      plazaId: this.plazaId
    }
    this.service.bookAPlaza(bookAPlazaDto).subscribe((res) => {
      console.log(res);
      this.message.success("Reserva realizada con éxito", { nzDuration: 5000 });
      this.router.navigateByUrl("/customer/dashboard");
    },error => {
      this.message.error("Ha habido un error", { nzDuration: 5000});
    })

  }


  checkIfDetailPage() {
    // Verifica si ya estamos en la página de detalle
    const currentUrl = window.location.href;
    this.isDetailPage = currentUrl.includes('book');
  }

}

import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AdminService } from "../../services/admin.service";
import { NzMessageService } from "ng-zorro-antd/message";
import { Router } from "@angular/router";

@Component({
  selector: 'app-plaza',
  templateUrl: './plaza.component.html',
  styleUrls: ['./plaza.component.scss']
})
export class PlazaComponent {
  plazaForm!: FormGroup;
  isSpinning: boolean = false;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  listOfPlazas = [
    "PLAZA 1", "PLAZA 2", "PLAZA 3", "PLAZA 4", "PLAZA 5",
    "PLAZA 6", "PLAZA 7", "PLAZA 8", "PLAZA 9", "PLAZA 10",
    "PLAZA 11", "PLAZA 12", "PLAZA 13", "PLAZA 14", "PLAZA 15"
  ];

  listOfType = ["Autocaravana", "Caravana", "Camper"];

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.plazaForm = this.fb.group({
      nombre: [null, Validators.required],
      precio: [null, [Validators.required, Validators.min(1)]],
      descripcion: [null, Validators.required],
      electricidad: [false],
      agua: [false],
      tipo: [null, Validators.required]
    });
  }

  plaza() {
    if (this.plazaForm.valid) {
      console.log(this.plazaForm.value);
      this.isSpinning = true;
      const formData: FormData = new FormData();
      if (this.selectedFile) {
        formData.append('image', this.selectedFile);
      }
      formData.append('nombre', this.plazaForm.get('nombre')!.value);
      formData.append('precio', this.plazaForm.get('precio')!.value);
      formData.append('descripcion', this.plazaForm.get('descripcion')!.value);
      formData.append('electricidad', this.plazaForm.get('electricidad')!.value);
      formData.append('agua', this.plazaForm.get('agua')!.value);
      formData.append('tipo', this.plazaForm.get('tipo')!.value);

      console.log(formData);
      this.adminService.plaza(formData).subscribe(
        res => {
          this.isSpinning = false;
          this.message.success("Plaza publicada con éxito", { nzDuration: 5000 });
          this.router.navigateByUrl("/admin/dashboard");
        },
        error => {
          this.isSpinning = false;
          this.message.error("Error al publicar la plaza", { nzDuration: 5000 });
        }
      );
    }
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
}

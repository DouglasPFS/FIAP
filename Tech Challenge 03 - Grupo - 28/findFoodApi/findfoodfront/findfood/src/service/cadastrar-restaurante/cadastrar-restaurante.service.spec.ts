import { TestBed } from '@angular/core/testing';

import { CadastrarRestauranteService } from './cadastrar-restaurante.service';

describe('CadastrarRestauranteService', () => {
  let service: CadastrarRestauranteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadastrarRestauranteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

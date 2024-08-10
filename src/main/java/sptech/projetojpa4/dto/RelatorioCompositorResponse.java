package sptech.projetojpa4.dto;

import sptech.projetojpa4.dominio.Compositor;

import java.util.List;

public class RelatorioCompositorResponse {

    private Long totalCompositores;

    public RelatorioCompositorResponse(Long totalCompositores) {
        this.totalCompositores = totalCompositores;
    }

    public Long getTotalCompositores() {
        return totalCompositores;
    }
}

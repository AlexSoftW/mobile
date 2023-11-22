package com.application.sallus_app.service

import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PacienteDetailsData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.model.UsuarioData
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PacienteService {
    @GET("clientes")
    suspend fun getTodosPacientes(): List<PacienteData>

    @GET("clientes/{id}")
    suspend fun getPacientePorId(@Path("id") id: Long): PacienteData

    @GET("clientes/com-vinculo/por-campos/{id}")
    suspend fun getPacientesComVinculoNutricionista(@Path("id") id: Long): List<PacienteDetailsData>

    @GET("clientes/busca-unico-nutricionista/{idCliente}")
    suspend fun getBuscarUnicoNutricionistaVinculado(
        @Path("idCliente") id: Long,
        @Query("idCliente") idQuery: Long,
    ): NutritionistData

    @POST("clientes/cadastrar")
    suspend fun adicionarCliente(@Body cliente: PacienteData): PacienteData

    @PUT("clientes")
    suspend fun atualizarPaciente(@Body dadosNutri: PerfilData)

    @PUT("clientes/atualizacaoSenha")
    suspend fun atualizarSenha(@Body novaSenha: UsuarioData)

    @PUT("clientes/mensagem/{idCliente}/{idNutri}")
    suspend fun vincularComNutricionista(
        @Path("idCliente") idCliente: Long,
        @Path("idNutri") idNutri: Long
    )

    @Multipart
    @PATCH("clientes/foto/{id}")
    suspend fun atualizarFoto(
        @Path("id") id: Long,
        @Part foto: MultipartBody.Part
    ): PacienteData

    @DELETE("clientes/desvincular/{id}")
    suspend fun desvincularPacienteComNutricionista(@Path("id") id: Long)

}
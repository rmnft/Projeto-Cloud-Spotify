package com.example.projetoBigCloud.controller;

import com.example.projetoBigCloud.controller.request.*;
import com.example.projetoBigCloud.models.*;
import com.example.projetoBigCloud.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    private final String PLAYLIST_CURTIDAS = "Musicas curtidas";

    @GetMapping
    public List<Usuario> obter(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> criar (@Valid @RequestBody Usuario usuario) {
        if (this.repository.findUsuarioByEmail(usuario.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Playlist playListCurtidas = new Playlist();
        playListCurtidas.setId(UUID.randomUUID());
        playListCurtidas.setNome(this.PLAYLIST_CURTIDAS);
        playListCurtidas.setUsuario(usuario);
        playListCurtidas.setIdUsuario(usuario.getId());

        this.repository.save(usuario);
        this.playlistRepository.save(playListCurtidas);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> obter(@PathVariable("id") UUID id) {
        return this.repository.findById(id).map(usuario -> {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginRequest request) {
        Optional<Usuario> optUsuario = this.repository.findUsuarioByEmailAndSenha(request.getEmail(), request.getSenha());

        return optUsuario.map(usuario -> {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("{id}/favoritar/{idMusica}")
    public ResponseEntity favoritar(@PathVariable("id") UUID id, @PathVariable("idMusica") UUID idMusica) {

        Optional<Usuario> optUsuario = this.repository.findById(id);

        Optional<Musica> optMusica = this.musicaRepository.findById(idMusica);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a musica a ser associada retornar um 404
        if (optMusica.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();
        Musica musica = optMusica.get();

        usuario.getPlaylists().get(0).getMusicas().add(musica);
        playlistRepository.save(usuario.getPlaylists().get(0));

        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PostMapping("{id}/desfavoritar/{idMusica}")
    public ResponseEntity desfavoritar(@PathVariable("id") UUID id, @PathVariable("idMusica") UUID idMusica) {

        //Faço as buscas do usuário e musica
        Optional<Usuario> optUsuario = this.repository.findById(id);

        Optional<Musica> optMusica = this.musicaRepository.findById(idMusica);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a musica a ser associada retornar um 404
        if (optMusica.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();
        Musica musica = optMusica.get();

        for(Musica item : usuario.getPlaylists().get(0).getMusicas()) {
            if (item.getId() == musica.getId()) {
                usuario.getPlaylists().get(0).getMusicas().remove(musica);
                break;
            }
        }
        playlistRepository.save(usuario.getPlaylists().get(0));
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PostMapping("{id}/criar-playlist")
    public ResponseEntity criarLista(@PathVariable("id") UUID id, @Valid @RequestBody PlaylistRequest request) {
        Optional<Usuario> optUsuario = this.repository.findById(id);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();

        Playlist playlist = new Playlist();
        playlist.setUsuario(usuario);
        playlist.setNome(request.getNome());

        playlistRepository.save(playlist);

        return new ResponseEntity(usuario, HttpStatus.OK);

    }

    @PostMapping("{id}/playlist/{idPlaylist}/adicionar/{idMusica}")
    public ResponseEntity adicionarMusicaPlaylist(@PathVariable("id") UUID id, @PathVariable("idPlaylist") UUID idPlayList, @PathVariable("idMusica") UUID idMusica) {
        //Faço as buscas do usuário, musica e playlist
        Optional<Usuario> optUsuario = this.repository.findById(id);
        Optional<Musica> optMusica = this.musicaRepository.findById(idMusica);
        Optional<Playlist> optPlaylist = this.playlistRepository.findById(idPlayList);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a playlist a ser associada a muscia retornar um 404
        if (optPlaylist.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a musica a ser associado retornar um 404
        if (optMusica.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Playlist playlist = optPlaylist.get();
        Musica musica = optMusica.get();

        //Adiciona na playlist
        playlist.getMusicas().add(musica);

        //Salva no banco de dados
        playlistRepository.save(playlist);

        return new ResponseEntity(optUsuario.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}/playlist/{idPlaylist}/remover/{idMusica}")
    public ResponseEntity removerMusicaPlaylist(@PathVariable("id") UUID id, @PathVariable("idPlaylist") UUID idPlaylist, @PathVariable("idMusica") UUID idMusica) {
        //Faço as buscas do usuário, musica e playlist
        Optional<Usuario> optUsuario = this.repository.findById(id);
        Optional<Musica> optMusica = this.musicaRepository.findById(idMusica);
        Optional<Playlist> optPlaylist = this.playlistRepository.findById(idPlaylist);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache o a lista a ser associada a musica retornar um 404
        if (optPlaylist.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a musica a ser associado retornar um 404
        if (optMusica.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Playlist playlist = optPlaylist.get();
        Musica musica = optMusica.get();

        //Adiciona na lista
        for(Musica item : playlist.getMusicas()) {
            if (item.getId() == musica.getId()) {
                playlist.getMusicas().remove(musica);
                break;
            }
        }

        //Salva no banco de dados
        playlistRepository.save(playlist);

        return new ResponseEntity(optUsuario.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable("id") UUID id, @RequestBody Usuario usuarioNovo) {
        Optional<Usuario> optUsuario = this.repository.findById(id);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();
        if(usuarioNovo.getNome() != null && !usuarioNovo.getNome().isEmpty()){
            usuario.setNome(usuarioNovo.getNome());
        }
        if (usuarioNovo.getEmail() != null && !usuarioNovo.getEmail().isEmpty()) {
            usuario.setEmail(usuarioNovo.getEmail());
        }
        if (usuarioNovo.getSenha() != null && !usuarioNovo.getSenha().isEmpty()) {
            usuario.setSenha(usuarioNovo.getSenha());
        }

        repository.save(usuario);

        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removerUsuario(@PathVariable UUID id) {
        Optional<Usuario> optUsuario = this.repository.findById(id);

        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();

        repository.delete(usuario);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("{id}/assinaturas/{idPlano}")
    public ResponseEntity adicionarAssinatura(@PathVariable UUID id, @PathVariable UUID idPlano) {
        Optional<Usuario> optUsuario = repository.findById(id);
        Optional<Plano> optPlano = planoRepository.findById(idPlano);


        //Caso não ache o usuário, retornar um 404
        if (optUsuario.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Caso não ache a playlist a ser associada a plano retornar um 404
        if (optPlano.isEmpty() == true) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = optUsuario.get();
        Plano plano = optPlano.get();

        if (!usuario.getAssinaturas().isEmpty()) {
            usuario.getAssinaturas().forEach(item -> {
                if (item.isAtivo()) {
                    item.setAtivo(false);
                }
            });
        }

        Assinatura assinatura = new Assinatura();

        assinatura.setAtivo(true);
        assinatura.setPlano(plano);
        assinatura.setUsuario(usuario);

        usuario.getAssinaturas().add(assinatura);

        assinaturaRepository.saveAndFlush(assinatura);

        //Salva no banco de dados
        repository.save(usuario);

        return new ResponseEntity<>(HttpStatus.OK);
     }



}

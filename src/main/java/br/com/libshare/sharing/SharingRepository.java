package br.com.libshare.sharing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SharingRepository extends JpaRepository<SharingEntity, Long> {

//	public SharingEntity findByUserOrigin(Long codUsu);
//
//	public SharingEntity findByUserDestiny(Long codUsu);
//
//	public SharingEntity findByUserOriginAndUserDestiny(Long codUsu);
}
package br.com.libshare.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
//
	public ProfileEntity findByCodUsu(Long CodUsu);
//
//	public List<ProfileEntity> findByName(String name);
//
//	public List<ProfileEntity> findByNameAndLastName(String lastName);
//
//	public List<ProfileEntity> findByAddress(String address);
//
//	//Buscar por Bairro
//	public List<ProfileEntity> findByNeighborhood(String neighborhood);

}

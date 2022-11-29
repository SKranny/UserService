package PersonService.mappers;

import PersonService.dto.AddressDTO;
import PersonService.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
}

import React from 'react';
import { Box, Collapse, Container, FormControl, FormHelperText, TextField } from '@mui/material';
import { LoadingButton } from '@mui/lab';
import { useForm } from 'react-hook-form';
import useAlert from '../../store/useAlert';
import PersonService from '../../services/PersonService';
import TextMaskCustom from '../../components/TextMaskCustom';
import HelpersUtil from '../../store/HelpersUtil';

interface FormValues {
    name: string,
    cpf: string,
    email: string,
    phone: string,
}
const Person: React.FC = () => {
    const { register, handleSubmit, formState: { errors } } = useForm<FormValues>();
    const [isSubmitting, setSubmitting] = React.useState<boolean>(false);

    const { showAlert, AlertComponent } = useAlert();

    const onSubmit = (data: FormValues) => {
        data.cpf = data.cpf.replace(/\D/g, '')
        console.log('data:', data);

        setSubmitting(true);
        setTimeout(() => {
            PersonService.save(data)
                .then(_response => {
                    showAlert({ type: "success", message: "Pessoa enviada com sucesso!", closeable: true });
                })
                .catch((_error) => {
                    showAlert({ type: "error", message: "Desculpe, não foi possível cadastrar.", closeable: true });
                })
                .finally(() => setSubmitting(false));
        }, 500);
    };

    return (
        <>
            <Container
                component="main"
                maxWidth="xs"
                className="mt-5"
                style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center' }}
            >
                <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        width: '100%',
                    }}
                >
                    <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <h3>Cadastro de Pessoa</h3>

                        {AlertComponent}

                        <FormControl variant="outlined" margin="normal" fullWidth>
                            <TextField
                                {...register('name')}
                                error={errors.name !== undefined}
                                label="Nome"
                                name="name"
                                autoComplete="name"
                                autoFocus
                                disabled={isSubmitting}
                            />
                            <Collapse in={errors.name !== undefined} unmountOnExit>
                                <FormHelperText id="name" error>{errors.name?.message}</FormHelperText>
                            </Collapse>
                        </FormControl>
                        <FormControl variant="outlined" margin="normal" fullWidth>
                            <TextField
                                {...register('cpf', { required: "Este campo é obrigatório", minLength: { value: 14, message: "Informe um CPF válido" } })}
                                error={errors.cpf !== undefined}
                                label="CPF"
                                name="cpf"
                                autoComplete="cpf"
                                autoFocus
                                inputProps={{
                                    mask: ["000.000.000-00"],
                                }}
                                InputProps={{
                                    inputComponent: TextMaskCustom as any,
                                }}
                                disabled={isSubmitting}
                            />
                            <Collapse in={errors.cpf !== undefined} unmountOnExit>
                                <FormHelperText id="cpf" error>{errors.cpf?.message}</FormHelperText>
                            </Collapse>
                        </FormControl>
                        <FormControl variant="outlined" margin="normal" fullWidth>
                            <TextField
                                {...register('email', {
                                    required: "Este campo é obrigatório",
                                    validate: (value) => HelpersUtil.isValidEmail(value) || 'Informe um e-mail válido'
                                })}
                                error={errors.email !== undefined}
                                label="E-mail"
                                name="email"
                                autoComplete="email"
                                autoFocus
                                disabled={isSubmitting}
                            />
                            <Collapse in={errors.email !== undefined} unmountOnExit>
                                <FormHelperText id="password" error>{errors.email?.message}</FormHelperText>
                            </Collapse>
                        </FormControl>
                        <FormControl variant="outlined" margin="normal" fullWidth>
                            <TextField
                                {...register('phone', { minLength: { value: 14, message: "Informe um telefone válido" } })}
                                error={errors.phone !== undefined}
                                label="Telefone"
                                name="phone"
                                autoComplete="phone"
                                autoFocus
                                inputProps={{
                                    mask: ["(00) 0000-0000", "(00) 90000-0000"],
                                }}
                                InputProps={{
                                    inputComponent: TextMaskCustom as any,
                                }}
                                disabled={isSubmitting}
                            />
                            <Collapse in={errors.phone !== undefined} unmountOnExit>
                                <FormHelperText id="phone" error>{errors.phone?.message}</FormHelperText>
                            </Collapse>
                        </FormControl>
                        <LoadingButton
                            loading={isSubmitting}
                            loadingPosition="start"
                            variant="contained"
                            type="submit"
                            sx={{ my: 2 }}
                            fullWidth
                        >
                            <span>Enviar</span>
                        </LoadingButton>
                    </Box>
                </Box>
            </Container>
        </>
    );
}

export default Person;